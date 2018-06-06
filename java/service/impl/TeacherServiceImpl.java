package service.impl;

import dto.ManyExam;
import dto.SingleExam;
import entity.Exam;
import entity.Score;
import entity.Student;
import exception.ServiceException;
import mapper.ExamMapper;
import mapper.ScoreMapper;
import mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.TeacherService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl implements TeacherService{

    private static final int MAX_STU_SUM = 80;

    private static final Comparator<SingleExam> COMPARATOR = new Comparator<SingleExam>() {
        public int compare(SingleExam o1, SingleExam o2) {
            if(o1.getStuNum()<o2.getStuNum())
                return -1;
            if(o1.getStuNum()==o2.getStuNum())
                return 0;
            return 1;
        }
    };

    @Autowired
    private StudentMapper studentMapper;

    @Autowired
    private ExamMapper examMapper;

    @Autowired
    private ScoreMapper scoreMapper;

    @Transactional(readOnly = true)
    public List<Student> selectAllStudent() {
        return studentMapper.selectAll();
    }

    public int updateStudentById(Student student) {
        int result =  studentMapper.updateById(student);
        if(result!=1)
            throw new ServiceException("编号为"+student.getStuNum()+"的学生不存在");
        return result;
    }

    public void deleteAll() {
        scoreMapper.deleteAll();
        examMapper.deleteAll();
        studentMapper.deleteAll();
    }

    public int insertManyStudent(List<Student> students) {
        return studentMapper.insertMany(students);
    }

    @Transactional(readOnly = true)
    public List<Exam> selectAllExam() {
        return examMapper.selectAll();
    }

    public int insertOneExam(Exam exam) {
        return examMapper.insertOne(exam);
    }

    public int deleteExamById(Integer id) {
        scoreMapper.deleteByExamId(id);
        return examMapper.deleteById(id);
    }

    protected List<Score> correctRank(List<Score> exam){
        int j = 0,realRank = 0;
        for(int i=0;i<exam.size();i++){
            j = i-1;
            while (j>=0&&exam.get(i).getChineseScore().equals(exam.get(j).getChineseScore())){
                j--;
            }
            realRank = j<0?1:exam.get(i).getChineseRank()-i+j+1;
            exam.get(i).setChineseRank(realRank);
        }
        return exam;
    }

    protected List<SingleExam> generateSingleExam(List<Score> lastExam,List<Score> curExam){
        List<SingleExam> singleExams = new ArrayList<>(MAX_STU_SUM);
        if(curExam==null||curExam.isEmpty())
            return singleExams;
        if(lastExam==null||lastExam.isEmpty())
            return generateSingleExam(curExam);
        for(int i=0;i<curExam.size();i++){
            for(int j=0;i-j>=0||i+j<lastExam.size();j++){
                if(i-j>=0)
                    if(lastExam.get(i-j).getStuNum().equals(curExam.get(i).getStuNum())){
                        singleExams.add(new SingleExam(curExam.get(i),lastExam.get(i-j)));
                        break;
                    }
                if(i+j<lastExam.size())
                    if(lastExam.get(i+j).getStuNum().equals(curExam.get(i).getStuNum())){
                        singleExams.add(new SingleExam(curExam.get(i),lastExam.get(i+j)));
                        break;
                    }
            }
        }
        return singleExams;
    }

    protected List<SingleExam> generateSingleExam(List<Score> curExam){
        List<SingleExam> singleExams = new ArrayList<>(MAX_STU_SUM);
        for(Score score : curExam){
            singleExams.add(new SingleExam(score));
        }
        return singleExams;
    }
    @Transactional(readOnly = true)
    public List<SingleExam> selectScoreByExamId(Integer lastExamId,Integer curExamId) {
        List<SingleExam> singleExams = null;
        //取出考试的分数
        List<Score> curScores= scoreMapper.selectByExamId(curExamId);
        if(curScores.isEmpty())
            throw new ServiceException("当前考试编号为"+curExamId+"，内容为空");
        correctRank(curScores);
        //存在对比的考试
        if(lastExamId!=null){
            List<Score> lastScores = scoreMapper.selectByExamId(lastExamId);
            if(lastScores.isEmpty())
                throw new ServiceException("对比考试编号为"+lastExamId+"，内容为空");
            if(lastScores.size()!=curScores.size())
                throw new ServiceException("编号分别为"+lastExamId+","+curExamId+"的考试参考人数不一致");
            correctRank(lastScores);
            singleExams = generateSingleExam(lastScores,curScores);
        }
        else{
            singleExams = generateSingleExam(curScores);
        }
        return singleExams;
    }

    @Transactional(readOnly = true)
    public List<ManyExam> selectAllScore() {
        List<ManyExam> manyExams = new ArrayList<>(MAX_STU_SUM);
        //取出所有考试
        List<Exam> exams = examMapper.selectAll();
        if(exams.isEmpty()){
            return manyExams;
        }

        //取出所有考试的分数
        List<List<SingleExam>> singleExamsList  = new ArrayList<>(exams.size());
        List<Score> lastExam = null,curExam = null;
        List<SingleExam> temp = null;
        for(int i=0;i<exams.size();i++){
            curExam = scoreMapper.selectByExamId(exams.get(i).getId());
            if(curExam.isEmpty())
                throw new ServiceException("当前考试编号为"+exams.get(i).getId()+"，内容为空");
            correctRank(curExam);
            if(lastExam!=null&&lastExam.size()!=curExam.size())
                throw new ServiceException("编号分别为"+exams.get(i-1).getId()+","+
                        exams.get(i).getId()+"的考试参考人数不一致");
            temp = generateSingleExam(lastExam,curExam);
            temp.sort(COMPARATOR);
            singleExamsList.add(temp);
            lastExam = curExam;
        }

        ManyExam manyExam = null;
        for(int i = 0 ; i < singleExamsList.size() ; i++){
            for(int j = 0 ; j < singleExamsList.get(0).size() ; j++){
                if(i==0){
                    manyExam = new ManyExam(singleExamsList.size());
                    manyExam.setStuNum(singleExamsList.get(i).get(j).getStuNum());
                    manyExam.setStuName(singleExamsList.get(i).get(j).getStuName());
                    manyExams.add(manyExam);
                }
                manyExam = manyExams.get(j);
                manyExam.addExam(singleExamsList.get(i).get(j));
            }
        }
        return manyExams;
    }

    public int insertManyScore(List<Score> scores) {
        return scoreMapper.insertMany(scores);
    }

    public int updateScoreByExamIdAndStuId(Score score) {
        int result = scoreMapper.updateByExamIdAndStuId(score);
        if(result!=1)
            throw new ServiceException("考试编号为"+score.getExamId()+"与学生编号为"+
                    score.getStuNum()+"的组合不存在");
        return result;
    }


}
