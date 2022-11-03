package com.wyverno.commands;

import com.wyverno.commands.annotations.ConstructorForCreateCommand;

public class WarnOffline extends Command {

    private static final String COMMAND = "/warnoff";

    @ConstructorForCreateCommand
    public WarnOffline(String nickname, String reason, String prefix) {
        super(nickname, reason, prefix);
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + getReasonAndPrefix();
    }
}
