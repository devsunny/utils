package com.asksunny.cli.utils;

import java.util.ArrayList;

public class RemoteCommandParser {

	public static RemoteCommand[] parseCommand(String cmdLine) {
		ArrayList<ArrayList<String>> cmds =parseCommandText(cmdLine);
		 RemoteCommand[] cmdarryss = new  RemoteCommand[cmds.size()] ;
		for(int i=0; i<cmds.size(); i++){
			ArrayList<String> cmd = cmds.get(i);
			String[] cmdarray = new String[cmd.size()];
			cmd.toArray(cmdarray);
			cmdarryss[i] = (new RemoteCommand()).setCmdArray(cmdarray);
		}		 
		 return cmdarryss;
	}
	
	protected static ArrayList<ArrayList<String>> parseCommandText(String cmdLine) {
		ArrayList<ArrayList<String>> cmds = new ArrayList<ArrayList<String>>();
		ArrayList<String> cmd = new ArrayList<String>();

		int size = cmdLine.length();
		
		StringBuilder buf  = new StringBuilder();
		for (int i = 0; i < size; i++) {
			char c = cmdLine.charAt(i);
			switch (c) {
			case '"':
				for(int j=i+1; j<size; j++)
				{
					char c2 = cmdLine.charAt(j);
					if(c2=='"'){
						i=j;
						break;
					}else{
						buf.append(c2);
					}
				}
				break;
			case '\'':
				for(int j=i+1; j<size; j++)
				{
					char c2 = cmdLine.charAt(j);
					if(c2=='\''){
						i=j;
						break;
					}else{
						buf.append(c2);
					}
				}
				break;
			case '\t':
			case ' ':
				if(buf.length()>0){
					cmd.add(buf.toString());
					buf.setLength(0);
				}
				break;
			case ';':
				if(buf.length()>0)	{
					cmd.add(buf.toString());
					buf.setLength(0);
				}
				if(cmd.size()>0){
					cmds.add(cmd);
					cmd = new ArrayList<String>();
				}				
				break;
			default:
				buf.append(c);
				break;
			}
			if(i==size-1){				
				if(buf.length()>0)	{
					cmd.add(buf.toString());
				}
				if(cmd.size()>0) cmds.add(cmd);					
			}
		}
		return cmds;
	}
	
	public static void main(String[]  args)
	{
		 RemoteCommand[]  cmds = parseCommand("cmd.exe /c \"dir src\";exit");
		for (RemoteCommand cmd : cmds) {
			for (String string : cmd.getCmdArray()) {
				System.out.println(string);
			}
		}
	}

}
