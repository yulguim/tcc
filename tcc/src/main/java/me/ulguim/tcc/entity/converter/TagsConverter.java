package me.ulguim.tcc.entity.converter;

/**
 * Created by yulle on 07/03/17.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.ulguim.tcc.bean.TagBean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Converter
public class TagsConverter implements AttributeConverter<List<TagBean>, String> {

	private final Gson gson = new GsonBuilder().create();

	@Override
	public String convertToDatabaseColumn(List<TagBean> socialNetworkList) {
		return gson.toJson(socialNetworkList);
	}

	@Override
	public List<TagBean> convertToEntityAttribute(String json) {
		if(json == null) return new ArrayList<>();
		Type type = new TypeToken<List<TagBean>>(){}.getType();
		return gson.fromJson(json, type);
	}

}
