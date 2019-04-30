package interview.parrot.questions.yelplike.entity;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import javax.persistence.AttributeConverter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author interviewparrot created on 03-Mar-2019
 */
public class ListConverterJson implements AttributeConverter<List<HoursOfOperation>, String> {

    Gson gson = new Gson();
    Type listType = new TypeToken<ArrayList<HoursOfOperation>>(){}.getType();

    @Override
    public String convertToDatabaseColumn(List<HoursOfOperation> attribute) {
        return gson.toJson(attribute);
    }

    @Override
    public List<HoursOfOperation> convertToEntityAttribute(String dbData) {
        return gson.fromJson(dbData, listType);
    }
}
