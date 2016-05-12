<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@include file="../base/taglib.jspf"%>
	<form id="userForm" class="form-horizontal" action="sys/userModify.do" method="post">
		<div class="form-group">
			<label  for="name">姓名</label>
			<input id="name" class="form-control" type="text" name="name" value="${user.name }" placeholder="员工姓名" valid="required" />
		</div>
		<div class="form-group">
			<label  for="userName">用户名</label>
			<input type="text" id="userName" class="form-control" name="userName"
					placeholder="用户名与工号一致" value="${user.userName }" valid="required"> <input
					type="hidden" name="id" value="${user.id }" >
		</div>
		<div class="form-group">
			<label  for="password">密码</label>
				<input type="password" id="password" class="form-control" name="password"
					placeholder="用户登录密码" value="${user.password }" valid="required">
		</div>
		<div class="form-group">
			<button type="button" class="btn btn-primary form-control" onclick="valid('#userForm',save);">保存修改</button>
		</div>
	</form>
