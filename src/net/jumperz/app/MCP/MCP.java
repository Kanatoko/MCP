package net.jumperz.app.MCP;

import net.jumperz.io.multicore.MMultiCoreParser;

public class MCP
{
private static int threadCount = -1;
private static int batchSize = -1;
private static String className;
//--------------------------------------------------------------------------------
public static void main( String[] args )
throws Exception
{
for( int i = 0; i < args.length; ++i )
	{
	if( args[ i ].equals( "--thread-count" ) )
		{
		threadCount = Integer.parseInt( args[ i + 1 ] );
		++i;
		}
	else if( args[ i ].equals( "--batch-size" ) )
		{
		batchSize = Integer.parseInt( args[ i + 1 ] );
		++i;
		}
	else
		{
		className = args[ i ];
		}
	}

MMultiCoreParser mcp = new MMultiCoreParser( Class.forName( className )  );
if( threadCount > 0 )
	{
	mcp.setThreadCount( threadCount );
	}
if( batchSize > -1 )
	{
	mcp.setBatchSize( batchSize );
	}
mcp.execute();
}
//--------------------------------------------------------------------------------
}