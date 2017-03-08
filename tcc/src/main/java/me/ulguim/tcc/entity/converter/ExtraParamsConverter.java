package me.ulguim.tcc.entity.converter;

/**
 * Created by yulle on 07/03/17.
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.ulguim.tcc.bean.ExtraParamsBean;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class ExtraParamsConverter implements AttributeConverter<ExtraParamsBean, String> {

	private final Gson gson = new GsonBuilder().create();

	@Override
	public String convertToDatabaseColumn(ExtraParamsBean bean) {
		return gson.toJson(bean);
	}

	@Override
	public ExtraParamsBean convertToEntityAttribute(String json) {
		if(json == null) return new ExtraParamsBean();
		return gson.fromJson(json, ExtraParamsBean.class);
	}

}
