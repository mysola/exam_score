package convert;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.github.pagehelper.Page;
import dto.ManyExam;
import dto.PageBean;
import entity.Exam;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.List;

public class ManyExamPageBeanMessageConverter extends MappingJackson2HttpMessageConverter {

    private List supportedMediaTypes = Arrays.asList(MediaType.APPLICATION_JSON);

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return supportedMediaTypes;
    }

    @Override
    public boolean canWrite(Type type, Class<?> aClass, MediaType mediaType) {
        if (!(type instanceof ParameterizedType))
            return false;
        ParameterizedType parameterizedType = (ParameterizedType) type;
        if (!(parameterizedType.getRawType() instanceof Class))
            return false;
        if (!PageBean.class.isAssignableFrom((Class) parameterizedType.getRawType()))
            return false;
        if (!ManyExam.class.isAssignableFrom((Class) parameterizedType.getActualTypeArguments()[0]))
            return false;
        if (mediaType == null || MediaType.ALL.equals(mediaType)) {
            return true;
        }
        for (MediaType supportedMediaType : getSupportedMediaTypes()) {
            if (supportedMediaType.includes(mediaType)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean canRead(Type type, Class<?> aClass, MediaType mediaType) {
        return false;
    }

    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        PageBean pageBean = (PageBean) object;
        List<ManyExam> manyExams = pageBean.getRows();
        ArrayNode arrayNode = objectMapper.createArrayNode();
        for(ManyExam manyExam : manyExams){
            ObjectNode objectNode = objectMapper.createObjectNode();
            objectNode.put("stuNum",manyExam.getStuNum());
            objectNode.put("stuName",manyExam.getStuName());
            for(int i=0;i<manyExam.getExamSum();i++){
                objectNode.put("e"+i+"Score",manyExam.getStuScores().get(i));
                objectNode.put("e"+i+"Rank",manyExam.getStuRanks().get(i));
                objectNode.put("e"+i+"ScoreVary",manyExam.getScoreVarys().get(i));
                objectNode.put("e"+i+"RankVary",manyExam.getRankVarys().get(i));
            }
            arrayNode.add(objectNode);
        }
        ObjectNode objectNode = objectMapper.createObjectNode();
        objectNode.put("total",pageBean.getTotal());
        objectNode.set("rows",arrayNode);
        outputMessage.getBody().write(objectNode.toString().getBytes("utf-8"));
    }


}



