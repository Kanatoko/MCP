MCP(Multi Core Parser)
================================

Java framework for multicore-aware log parsing

Download Binary
-------------------------
http://www.jumperz.net/tools/mcp.jar

Build
-------------------------
ant build

Run
-------------------------
cat YOUR_TEXT_FILE | java -cp output/mcp.jar net.jumperz.app.MCP.MCP YOUR_CLASS_NAME 
( Note that your class files must be in the classpath )

Example
-------------------------
echo -e 'foo\nbar\nbaz' | java -cp output/mcp.jar net.jumperz.app.MCP.MCP UpperCase

How to implement your parser
-------------------------
Your class needs to implement the 'net.jumperz.io.multicore.MParser' interface like below.

```Java
public class UpperCase
implements net.jumperz.io.multicore.MParser
{
//---------------------------------------------
public String parse( String s )
{
return s.toUpperCase();
}
//---------------------------------------------
}
```
