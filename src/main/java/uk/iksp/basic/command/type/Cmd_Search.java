package uk.iksp.basic.command.type;

import java.util.ArrayList;
import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.entity.command.InputCommand;
import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;
import com.ksptooi.asmc.service.command.CommandDataService;

public class Cmd_Search implements Command_cmd{

	@Override
	public void ExecuteOfType(Command command) {
		
		
		InputCommand ic = command.getInputCommand();
		
		ArrayList<String> parameter = ic.getParameter();
		Logger msg = Asmc.getLogger();
		
		
		if(parameter.size()<1){
			msg.sendWarningMessage("用法: ?>参数1");
			return;
		}
		
		String cmName = parameter.get(0);
		
		CommandDataService cs = Asmc.getCommandDataService();
		
		
		ArrayList<Command> allCommand = cs.queryList(null);
		
		ArrayList<Command> list = new ArrayList<Command>();
		
		for(Command c:allCommand){
			
			if(c.getName().contains(cmName)){
				list.add(c);
			}
			
		}
		
		
		if(list.size()<1){
			msg.sendMessage("用法: ?>参数1ַ");
			return;
		}
		
		
		String R2="|   命令名   | 类型 |    地址    |     标题     |  CCA  |";
		System.out.println(R2);
		
		
		String name="";
		String type="";
		String path="";
		String title="";
		String CCA="";		
		msg.sendBr();
		
		for(Command ce:list){
			
			boolean isDir = ce.getFile().equalsIgnoreCase("");
			
			name="|"+ce.getName();
			type=""+isDir;
			path=""+ce.getType();
			title=""+ce.getTitle();
			CCA=""+ce.getCca();
			
			
			while(name.length()<=9){
				name=name+" ";
			}
			name=name+"|";
			
			while(type.length()<=9){
				type=type+" ";
			}
			type=type+"|";
			
			while(path.length()<=12){
				path=path+" ";
			}
			path=path+"|";
			
			while(title.length()<=12){
				title=title+" ";
			}
			title=title+"|";
			
			while(CCA.length()<=8){
				CCA=CCA+" ";
			}
			CCA=CCA+"|";
			
			
			System.out.println(name+type+path+title+CCA);	
			
		}
		
		
		
	}


	
	
	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	
	
	
}
