/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.reiki.infra.util;

import java.util.Date;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

public class Util {

	public static final String IGNORA = "ignora";
	public static final String NGNORA = "ngnora";
	public static final long INTEGERPARANAONULO = 0;
	public static final long LONGPARANAONULO = -100;
	public static final double DOUBLEPARANAONULO = -100.0;
	public static final String STRINGPARANAONULO = "";
	public static final Character CHARPARANAONULO = ' ';

	public static boolean isStringEmpty(String s) {
		boolean b = false;
		if (s != null && s.length() == 0 && s.trim().equals("")) {
			b = true;
		}
		return b;
	}

	public static boolean verificarErroIntegrityConstraint(Throwable throwable) {
		if (throwable != null) {
			if (throwable instanceof MySQLIntegrityConstraintViolationException) {
				return true;
			} else {
				if (throwable.getCause() != null) {
					return verificarErroIntegrityConstraint(throwable.getCause());
				} else {
					return false;
				}
			}
		}
		return false;
	}

	public static String defineSeIgnorar(Integer obj) {
		String ig = IGNORA;
		if (obj != null && obj.intValue() != INTEGERPARANAONULO) {
			ig = NGNORA;
		}
		return ig;
	}

	public static String defineSeIgnorar(Long obj) {
		String ig = IGNORA;
		if (obj != null && obj.longValue() != LONGPARANAONULO) {
			ig = NGNORA;
		}
		return ig;
	}

	public static String defineSeIgnorar(Double obj) {
		String ig = IGNORA;
		if (obj != null && obj.doubleValue() != DOUBLEPARANAONULO) {
			ig = NGNORA;
		}
		return ig;
	}

	public static String defineSeIgnorar(String obj) {
		String ig = IGNORA;
		if (obj != null && !obj.equals(STRINGPARANAONULO)) {
			ig = NGNORA;
		}
		return ig;
	}

	public static String defineSeIgnorar(Character obj) {
		String ig = IGNORA;
		if (obj != null && !obj.equals(CHARPARANAONULO)) {
			ig = NGNORA;
		}
		return ig;
	}

	public static String defineSeIgnorar(Date obj) {
		String ig = IGNORA;
		if (obj != null) {
			ig = NGNORA;
		}
		return ig;
	}
}
