package com.wyverno.commands;

import com.wyverno.commands.annotations.ConstructorForCreateCommand;
import com.wyverno.commands.annotations.FillableParameter;
import com.wyverno.commands.annotations.NameParameter;

public class MuteOffline extends Command {

    private static final String COMMAND = "/muteoff";

    @FillableParameter
    public int time;

    @ConstructorForCreateCommand
    public MuteOffline(@NameParameter(name = "nickname") String nickname,
                       @NameParameter(name = "reason") String reason,
                       @NameParameter(name = "prefix") String prefix,
                       @NameParameter(name = "time") int time) {
        super(nickname, reason, prefix);
        this.time = time;
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + time + " " + getReasonAndPrefix();
    }
}
