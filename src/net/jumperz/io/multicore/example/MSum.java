package net.jumperz.io.multicore.example;

import net.jumperz.io.multicore.MParser;

public class MSum
implements MParser
{
private static long sum;

static
{
try
	{
	Runtime.getRuntime().addShutdownHook(
		
	new Thread()
		{
		public void run()
			{
			System.out.println( sum );
			}
		} );
	
	}
catch( Exception ignored )
	{
	}
}
//--------------------------------------------------------------------------------
public String parse( String s )
{
try
	{
	long l = Long.parseLong( s );
	synchronized( MSum.class )
		{
		sum += l;
		}
	}
catch( Exception ignored )
	{
	}
return null;
}
//--------------------------------------------------------------------------------
}