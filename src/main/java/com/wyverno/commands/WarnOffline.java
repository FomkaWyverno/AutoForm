package com.wyverno.commands;

import com.wyverno.commands.annotations.ConstructorForCreateCommand;
import com.wyverno.commands.annotations.NameParameter;

public class WarnOffline extends Command {

    private static final String COMMAND = "/warnoff";

    @ConstructorForCreateCommand
    public WarnOffline(@NameParameter(name = "nickname") String nickname,
                       @NameParameter(name = "reason") String reason,
                       @NameParameter(name = "prefix") String prefix) {
        super(nickname, reason, prefix);
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + getReasonAndPrefix();
    }
}
