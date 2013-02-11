package net.jumperz.io.multicore;

import java.io.*;
import java.util.*;

public class MWriter
{
private OutputStream out;
private volatile int writeIndex;
private String charset;
private Object mutex = new Object();
//--------------------------------------------------------------------------------
public MWriter( OutputStream out, String charset )
throws IOException
{
this.out = new BufferedOutputStream( out );
this.charset = charset;
}
//--------------------------------------------------------------------------------
public void write2( Map data )
throws IOException
{
while( true )
{
int index = ( ( Integer )data.get( "index" ) ).intValue();
boolean ok = false;
synchronized( this )
	{
	if( writeIndex == index )
		{
		ok = true;
		}
	}

if( ok )
	{
	List list = ( List )data.get( "data" );
	for( int i = 0; i < list.size(); ++i )
		{
		String line = ( String )list.get( i );
		if( line != null )
			{
			out.write( line.getBytes( charset ) );
			out.write( ( byte ) 0x0A );
			}
		}
	out.flush();
	++writeIndex;
	try
		{
		synchronized( this )
			{
			this.notifyAll();
			}
		}
	catch( Exception e )
		{
		e.printStackTrace();
		}
	return;	
	}
else
	{
	try
		{
		//System.err.println( "waiting:" + index + ":" + writeIndex );
		synchronized( this )
			{
			this.wait();
			}
		}
	catch( Exception e )
		{
		e.printStackTrace();
		break;
		}
	}
}
/*
while( true )
	{
	int index = ( ( Integer )data.get( "index" ) ).intValue();
	if( writeIndex == index )
		{
		List list = ( List )data.get( "data" );
		for( int i = 0; i < list.size(); ++i )
			{
			String line = ( String )list.get( i );
			out.write( line.getBytes( charset ) );
			out.write( ( byte ) 0x0A );
			}
		out.flush();
		++writeIndex;
		try
			{
			//synchronized( mutex )
				{
				this.notifyAll();
				}
			}
		catch( Exception e )
			{
			e.printStackTrace();
			}
		return;
		}
	else
		{
		try
			{
			//System.err.println( "waiting:" + index + ":" + writeIndex );
			//synchronized( mutex )
				{
				this.wait();
				}
			}
		catch( Exception e )
			{
			e.printStackTrace();
			break;
			}
		}
	}
*/
}
//--------------------------------------------------------------------------------
public synchronized void write( Map data )
throws IOException
{
while( true )
	{
	int index = ( ( Integer )data.get( "index" ) ).intValue();
	if( writeIndex == index )
		{
		List list = ( List )data.get( "data" );
		for( int i = 0; i < list.size(); ++i )
			{
			String line = ( String )list.get( i );
			out.write( line.getBytes( charset ) );
			out.write( ( byte ) 0x0A );
			}
		out.flush();
		++writeIndex;
		try
			{
			//synchronized( mutex )
				{
				this.notifyAll();
				}
			}
		catch( Exception e )
			{
			e.printStackTrace();
			}
		return;
		}
	else
		{
		try
			{
			//System.err.println( "waiting:" + index + ":" + writeIndex );
			//synchronized( mutex )
				{
				this.wait();
				}
			}
		catch( Exception e )
			{
			e.printStackTrace();
			break;
			}
		}
	}
}
//--------------------------------------------------------------------------------
}