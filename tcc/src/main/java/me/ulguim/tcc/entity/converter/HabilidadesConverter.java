package me.ulguim.tcc.entity.converter;

/**
 * Created by yulle on 07/03/17.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import me.ulguim.tcc.bean.ComentarioBean;
import me.ulguim.tcc.bean.HabilidadeBean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

@Converter
public class HabilidadesConverter implements AttributeConverter<List<HabilidadeBean>, String> {

	private final Gson gson = new GsonBuilder().create();

	@Override
	public String convertToDatabaseColumn(List<HabilidadeBean> habilidadeList) {
		return gson.toJson(habilidadeList);
	}

	@Override
	public List<HabilidadeBean> convertToEntityAttribute(String json) {
		if(json == null) return new ArrayList<>();
		Type type = new TypeToken<List<HabilidadeBean>>(){}.getType();
		return gson.fromJson(json, type);
	}

}
