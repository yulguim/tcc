package me.ulguim.tcc.entity.converter;

/**
 * Created by yulle on 07/03/17.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.ulguim.tcc.bean.NotificationBean;
import me.ulguim.tcc.bean.SocialNetworkBean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Converter
public class SocialNetworkConverter implements AttributeConverter<List<SocialNetworkBean>, String> {

	private final Gson gson = new GsonBuilder().create();

	@Override
	public String convertToDatabaseColumn(List<SocialNetworkBean> socialNetworkList) {
		return gson.toJson(socialNetworkList);
	}

	@Override
	public List<SocialNetworkBean> convertToEntityAttribute(String json) {
		if(json == null) return new ArrayList<>();
		Type type = new TypeToken<List<SocialNetworkBean>>(){}.getType();
		return gson.fromJson(json, type);
	}

}
