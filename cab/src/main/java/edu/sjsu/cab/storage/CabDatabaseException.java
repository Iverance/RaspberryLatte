package edu.sjsu.cab.storage;

public class CabDatabaseException extends Exception {

	//private static final long serialVersionUID = 7972154586218627900L;

	public CabDatabaseException(Throwable cause)
	{
		super(cause);
	}

	public CabDatabaseException(String message, Throwable cause)
	{
		super(message, cause);
	}

	public CabDatabaseException(String message)
	{
		super(message);
	}

	public CabDatabaseException()
	{
		super();
	}
}
