package com.jet.vframe.sys.tool;

import java.io.Serializable;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.id.IdentifierGenerator;

public class UUID implements IdentifierGenerator {

	public UUID() {
		// TODO Auto-generated constructor stub
	}

	public static String generate() {
		String uuid = java.util.UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}

	@Override
	public Serializable generate(SessionImplementor arg0, Object arg1)
			throws HibernateException {
		// TODO Auto-generated method stub
		return generate();
	}
}
