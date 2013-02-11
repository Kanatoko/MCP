package net.jumperz.app.MFind2;

import net.jumperz.util.MLogServer;

public abstract class MAbstractLogAgent
//implements MConstants
{
protected String className = "";
protected String prefix = "";
// --------------------------------------------------------------------------------
public void log( int logLevel, Object message )
{
MLogServer.getInstance().log( className, logLevel, prefix, message );
}
// --------------------------------------------------------------------------------
public void info( Object message )
{
log( MLogServer.log_info, message );
}
// --------------------------------------------------------------------------------
public void warn( Object message )
{
log( MLogServer.log_warn, message );
}
// --------------------------------------------------------------------------------
public void debug( Object message )
{
log( MLogServer.log_debug, message );
}
// --------------------------------------------------------------------------------
public void log( String _prefix, String message )
{
MLogServer.getInstance().log( className, MLogServer.log_info, _prefix, message );
}
// --------------------------------------------------------------------------------
public void log( String message )
{
MLogServer.getInstance().log( className, MLogServer.log_info, prefix, message );
}
// --------------------------------------------------------------------------------
protected void getExceptionLog( Throwable e, int logLevel )
{
MLogServer.getInstance().log( className, logLevel, "ERR", e );
}
//--------------------------------------------------------------------------------
protected void getExceptionLog( Throwable e )
{
getExceptionLog( e, MLogServer.log_info );
}
// --------------------------------------------------------------------------------
}