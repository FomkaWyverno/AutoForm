package com.wyverno.commands;

import com.wyverno.commands.annotations.ConstructorForCreateCommand;
import com.wyverno.commands.annotations.FillableParameter;
import com.wyverno.commands.annotations.NameParameter;

public class JailOffline extends Command {

    private static final String COMMAND = "/jailoff";

    @FillableParameter
    public int time;

    @ConstructorForCreateCommand
    public JailOffline(String nickname, String reason, String prefix,
                       @NameParameter(name = "time") int time) {
        super(nickname, reason, prefix);
        this.time = time;
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + time + " " + this.getReasonAndPrefix();
    }
}