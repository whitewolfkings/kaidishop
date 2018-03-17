package cn.mmk.web.action.privilege;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 权限配置
 *
 */
@Retention(RetentionPolicy.RUNTIME) //代表Permission注解保留在的阶段
@Target(ElementType.METHOD)
public @interface Permission {
	/** 模块 */
	String module();
	/** 权限值 */
	String privilege();
}
