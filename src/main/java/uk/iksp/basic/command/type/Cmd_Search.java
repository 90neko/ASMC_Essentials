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
		
		//判断参数长度
		if(parameter.size()<1){
			msg.sendWarningMessage("搜索语句:>要搜索的名称");
			return;
		}
		
		String cmName = parameter.get(0);
		
		CommandDataService cs = Asmc.getCommandDataService();
		
		//获取所有命令
		ArrayList<Command> allCommand = cs.queryList(null);
		
		ArrayList<Command> list = new ArrayList<Command>();
		
		//筛选命令
		for(Command c:allCommand){
			
			if(c.getName().contains(cmName)){
				list.add(c);
			}
			
		}
		
		//判断筛选后的命令
		if(list.size()<1){
			msg.sendMessage("没有返回任何结果!");
			return;
		}
		
		
		String R2="|   命令名  | 是否为目录 |    类型    |     描述    |  CCA  |";
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
