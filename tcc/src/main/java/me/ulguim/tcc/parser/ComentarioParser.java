package me.ulguim.tcc.parser;

import in.k2s.sdk.util.data.DataUtil;
import in.k2s.sdk.web.view.parse.BaseParser;
import me.ulguim.tcc.bean.ComentarioBean;
import me.ulguim.tcc.entity.Post;
import me.ulguim.tcc.view.ComentarioView;
import me.ulguim.tcc.view.PostView;

/**
 * Created by yulle on 09/03/17.
 */
public class ComentarioParser extends BaseParser {

	public static ComentarioView parse(ComentarioBean bean) {
		ComentarioView view = new ComentarioView();
		view.setAuthorId(bean.getIdUsuario());
		view.setAuthorLabel(bean.getLabelUsuario());
		view.setComentario(bean.getComentario());
		view.setInsertTime(bean.getInsertTime());

		return view;
	}

}
