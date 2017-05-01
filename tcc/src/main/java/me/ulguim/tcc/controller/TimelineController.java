package me.ulguim.tcc.controller;

import in.k2s.sdk.springboot.controller.annotation.ControllerSecurity;
import me.ulguim.tcc.controller.base.TCCBaseController;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerSecurity(ControllerSecurity.Security.PRIVATE)
@RequestMapping(value="/timeline", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TimelineController extends TCCBaseController {



}
