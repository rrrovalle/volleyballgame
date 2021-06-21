package view.command;

import java.util.ArrayList;
import java.util.List;

public class CommandInvoker {

	   private List<Command> commands = new ArrayList<>();

	   	public void add(Command comm) {
	        commands.add(comm);
	    }

	   	public void execute() {
	        for (Command comm : commands) {
	            comm.execute();
	        }
	        commands.clear();
	    }
}