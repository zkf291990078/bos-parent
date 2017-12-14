package com.itheima.bos.web.action.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

	protected T model;

	public BaseAction() {
		// TODO Auto-generated constructor stub
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		Type[] types = (Type[]) type.getActualTypeArguments();
		Class<T> clazz = (Class<T>) types[0];
		try {
			model=clazz.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public T getModel() {
		// TODO Auto-generated method stub
		return model;
	}

}
