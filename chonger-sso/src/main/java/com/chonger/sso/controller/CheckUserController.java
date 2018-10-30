package com.chonger.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chonger.common.util.ObjectUtil;
import com.chonger.common.vo.SysResult;
import com.chonger.sso.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
public class CheckUserController {
	/*URL	http://sso.jt.com/user/check/{param}/{type}
		参数	格式如：chenchen/1
		其中chenchen是校验的数据
		Type为类型，可选参数1 username、2 phone、3 email
*/
	@Autowired
	UserService userService;
	
	@RequestMapping("user/check/{param}/{type}")
	public String CheckUser(@PathVariable String param,
			@PathVariable Integer type,String callback) throws Exception{
		SysResult result=userService.checkParam(param,type);
		return callback+"("+ObjectUtil.mapper.writeValueAsString(result)+")";
		
	}
}
