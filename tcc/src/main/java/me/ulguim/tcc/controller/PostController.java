package me.ulguim.tcc.controller;

import in.k2s.sdk.web.validation.ValidationException;
import me.ulguim.tcc.controller.base.TCCBaseController;
import me.ulguim.tcc.manager.ContatosManager;
import me.ulguim.tcc.manager.PostManager;
import me.ulguim.tcc.view.ComentarioView;
import me.ulguim.tcc.view.ContatoView;
import me.ulguim.tcc.view.PostView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/post", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class PostController extends TCCBaseController {

	@Autowired
	private PostManager postManager;

	@RequestMapping(method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public PostView save(@RequestBody PostView view) throws ValidationException {
		return postManager.save(getProfile(), view);
	}

	@RequestMapping(value="/{view.key}", method = RequestMethod.DELETE)
	public PostView delete(PostView view) throws ValidationException {
		return postManager.delete(getProfile(), view);
	}

	/* COMENTARIOS */

	@RequestMapping(value="/save-comment", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ComentarioView saveComentario(@RequestBody ComentarioView view) throws ValidationException {
		return postManager.saveComentario(getProfile(), view);
	}

	@RequestMapping(value="/delete-comment", method = RequestMethod.POST, consumes = {MediaType.APPLICATION_JSON_UTF8_VALUE})
	public ComentarioView deleteComentario(@RequestBody ComentarioView view) throws ValidationException {
		return postManager.deleteComentario(getProfile(), view);
	}

}
