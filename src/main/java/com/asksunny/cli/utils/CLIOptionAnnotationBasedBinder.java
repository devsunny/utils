package com.asksunny.cli.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.PosixParser;

import com.asksunny.cli.utils.annotation.CLIOptionBinding;

/**
 * This utility class is created for easier and cleanner use of Apache common-cli sub-project,
 * Pojo class need to be annotated with com.asksunny.cli.utils.annotation.CLIOptionBinding
 * @author Sunny
 *
 */
public class CLIOptionAnnotationBasedBinder {
	public static CommandLine bindPosix(Options options, String[] args,
			Object pojo) throws CLIOptionBindingException {
		CommandLineParser parser = new PosixParser();
		CommandLine cli = null;
		try {
			cli = parser.parse(options, args);
			Class<? extends Object> clazz = pojo.getClass();

			Field[] fields = clazz.getFields();
			for (Field field : fields) {
				if (field.isAnnotationPresent(CLIOptionBinding.class)) {
					CLIOptionBinding cliBinding = field
							.getAnnotation(CLIOptionBinding.class);

					char shortOpt = cliBinding.shortOption();
					String longOpt = cliBinding.longOption();
					if (cliBinding.values()) {
						if (field.getType().isArray()) {
							field.set(pojo, cli.getArgs());
						} else if (cli.getArgs().length > 0) {
							field.set(
									pojo,
									getTypedvalue(field.getType(),
											cli.getArgs()[0],
											cliBinding.format()));

						}
					} else if (shortOpt != 0 && cli.hasOption(shortOpt)) {
						String val = (cli.getOptionValue(shortOpt) == null) ? "true"
								: cli.getOptionValue(shortOpt);
						field.set(
								pojo,
								getTypedvalue(field.getType(), val,
										cliBinding.format()));
					} else if (!longOpt.isEmpty() && cli.hasOption(longOpt)) {
						String val = (cli.getOptionValue(longOpt) == null) ? "true"
								: cli.getOptionValue(longOpt);

						field.set(
								pojo,
								getTypedvalue(field.getType(), val,
										cliBinding.format()));
					}

				}
			}
			Method[] methods = clazz.getMethods();
			for (Method method : methods) {
				if (method.isAnnotationPresent(CLIOptionBinding.class)
						&& method.getParameterTypes().length == 1) {
					CLIOptionBinding cliBinding = method
							.getAnnotation(CLIOptionBinding.class);
					char shortOpt = cliBinding.shortOption();
					String longOpt = cliBinding.longOption();
					if (cliBinding.values()) {
						Class<? extends Object>[] paramTypes = method
								.getParameterTypes();
						String[] sargs = cli.getArgs();
						if (paramTypes.length == 1 && paramTypes[0].isArray()) {
							method.invoke(pojo, (Object) sargs);
						} else if (paramTypes.length <= sargs.length) {
							Object[] params = new Object[paramTypes.length];
							for (int i = 0; i < params.length; i++) {
								params[i] = getTypedvalue(paramTypes[i],
										sargs[i], "");
							}
							method.invoke(pojo, params);
						}

					} else if (shortOpt != 0 && cli.hasOption(shortOpt)) {
						String val = (cli.getOptionValue(shortOpt) == null) ? "true"
								: cli.getOptionValue(shortOpt);
						method.invoke(
								pojo,
								getTypedvalue(method.getParameterTypes()[0],
										val, cliBinding.format()));
					} else if (!longOpt.isEmpty() && cli.hasOption(longOpt)) {
						String val = (cli.getOptionValue(longOpt) == null) ? "true"
								: cli.getOptionValue(longOpt);
						method.invoke(
								pojo,
								getTypedvalue(method.getParameterTypes()[0],
										val, cliBinding.format()));
					}

				}
			}
		} catch (Throwable e) {
			throw new CLIOptionBindingException(
					"Failed to bind cli option to POJO:" + pojo.toString());
		}

		return cli;

	}

	private static Object getTypedvalue(Class type, String strVal, String format)
			throws java.text.ParseException {
		Object obj = null;
		if (strVal == null)
			return null;
		if (type == int.class || type == Integer.class) {
			obj = Integer.valueOf(strVal);
		} else if (type == String.class) {
			obj = strVal;
		} else if (type == long.class || type == Long.class) {
			obj = strVal;
		} else if (type == double.class || type == Double.class) {
			obj = Double.valueOf(strVal);
		} else if (type == short.class || type == Short.class) {
			obj = Short.valueOf(strVal);
		} else if (type == byte.class || type == Byte.class) {
			obj = Byte.valueOf(strVal);
		} else if (type == boolean.class || type == Boolean.class) {
			obj = Boolean.valueOf(strVal);
		} else if (type == float.class || type == Float.class) {
			obj = Float.valueOf(strVal);
		} else if (type == BigDecimal.class) {
			obj = new BigDecimal(strVal);
		} else if (type == Date.class || type == java.sql.Date.class
				|| type == Time.class || type == Timestamp.class) {
			SimpleDateFormat sdf = (format == null) ? new SimpleDateFormat(
					"yyyy-MM-dd") : new SimpleDateFormat(format);
			obj = sdf.parse(strVal);
		} else {
			obj = strVal;
		}
		return obj;
	}
}
