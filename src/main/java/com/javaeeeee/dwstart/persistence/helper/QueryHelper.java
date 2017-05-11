package com.javaeeeee.dwstart.persistence.helper;

import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

public class QueryHelper {

	public static Path getExpression(Root root, String expr) {
		String[] path = expr.split("\\.");
		Path expression = root;
		for (String p : path) {
			expression = expression.get(p);
		}
		return expression;
	}
}
