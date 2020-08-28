package uk.iksp.basic.command.type;

import java.util.ArrayList;

import com.ksptooi.asmc.entity.command.Command;
import com.ksptooi.asmc.entity.command.InputCommand;
import com.ksptooi.asmc.entity.commandType.Command_cmd;
import com.ksptooi.asmc.main.Asmc;
import com.ksptooi.asmc.message.Logger;

public class Cmd_Del implements Command_cmd{

	@Override
	public void ExecuteOfType(Command command) {
		
		
		Logger msg = Asmc.getLogger();
		
		InputCommand ic = command.getInputCommand();
		
		ArrayList<String> parameter = ic.getParameter();
		
		//判断参数长度
		if(parameter.size()<1){
			msg.sendWarningMessage("删除语句:>名称");
			return;
		}
		
		Asmc.getCommandDataService().delete(Integer.valueOf(parameter.get(0)));
		
		msg.successMessage("已成功删除命令:"+ic.getParameter().get(0));
		
	}


	
	
	@Override
	public Command_cmd getThis() {
		return this;
	}

	
	
	
	
}
