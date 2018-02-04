package com.restrain.bean;

import com.restrain.annotation.Relevance;
import com.restrain.annotation.RelevanceClass;

import java.lang.reflect.Field;

public class MainBean {

	private Integer srmResult;
	private String srmMsg;

	public Integer getSrmResult() {
		return srmResult;
	}

	public void setSrmResult(Integer srmResult) {
		this.srmResult = srmResult;
	}

	public String getSrmMsg() {
		return srmMsg;
	}

	public void setSrmMsg(String srmMsg) {
		this.srmMsg = srmMsg;
	}

	public void inject(Object bean) {
		try {
			RelevanceClass relevanceClass = (RelevanceClass) this.getClass().getAnnotation(RelevanceClass.class);
			if (!relevanceClass.value().equals(bean.getClass().getSimpleName())) {
				Field[] toFields = this.getClass().getDeclaredFields();
				for (Field toField : toFields) {
					try {
						if (toField.isAnnotationPresent(Relevance.class)) {
							Relevance relevance = (Relevance) toField.getAnnotation(Relevance.class);
							String relevanceName = relevance.name();
							String relevanceField = relevance.field();

							if (relevanceName.equals(bean.getClass().getSimpleName())) {
								Field[] relevanceFields = bean.getClass().getDeclaredFields();
								for (Field field : relevanceFields) {
									if (relevanceField.equals(field.getName())) {
										field.setAccessible(true);
										toField.setAccessible(true);
										toField.set(this, field.get(bean));
										toField.setAccessible(false);
										field.setAccessible(false);
										break;
									}
								}
							}
						}
					} catch (Exception e) {
					}
				}
			} else {
				Field[] toFields = this.getClass().getDeclaredFields();
				for (Field toField : toFields) {
					try {
						String relevanceName = null;
						String relevanceField = null;
						if (toField.isAnnotationPresent(Relevance.class)) {
							Relevance relevance = (Relevance) toField.getAnnotation(Relevance.class);
							relevanceName = relevance.name();
							relevanceField = relevance.field();
						}
						if (relevanceName == null || relevanceField == null) {
							Field fromField = null;
							Field[] fields = bean.getClass().getDeclaredFields();
							for (Field field : fields) {
								if (toField.getName().equals(field.getName())) {
									fromField = field;
									break;
								}
							}
							if (fromField != null) {
								fromField.setAccessible(true);
								toField.setAccessible(true);
								toField.set(this, fromField.get(bean));
								fromField.setAccessible(false);
								toField.setAccessible(false);
							}
						} else {

							Field fromField = null;
							Field[] fields = bean.getClass().getDeclaredFields();
							for (Field field : fields) {
								if (relevanceName.equals(field.getName())) {
									fromField = field;
									break;
								}
							}
							if (fromField != null) {
								Field fromRelevanceField = null;
								Field[] relevanceFields = fromField.getType().getDeclaredFields();
								if (relevanceField.split(".").length == 2) {
									Field tempFromRelevanceField = null;
									String relevanceField1 = relevanceField.split(".")[0];
									String relevanceField2 = relevanceField.split(".")[1];
									for (Field field : relevanceFields) {
										if (relevanceField1.equals(field.getName())) {
											tempFromRelevanceField = field;
											break;
										}
									}
									if (tempFromRelevanceField != null) {
										Field[] tempRelevanceFields = tempFromRelevanceField.getType().getDeclaredFields();
										for (Field field : tempRelevanceFields) {
											if (relevanceField2.equals(field.getName())) {
												fromRelevanceField = field;
												break;
											}
										}
									}
								} else {
									for (Field field : relevanceFields) {
										if (relevanceField.equals(field.getName())) {
											fromRelevanceField = field;
											break;
										}
									}
								}

								if (fromRelevanceField != null) {
									fromField.setAccessible(true);
									fromRelevanceField.setAccessible(true);
									toField.setAccessible(true);
									if (fromField.get(bean) != null) {
										toField.set(this, fromRelevanceField.get(fromField.get(bean)));
									}
									toField.setAccessible(false);
									fromRelevanceField.setAccessible(false);
									fromField.setAccessible(false);
								}
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
		}
	}

}
