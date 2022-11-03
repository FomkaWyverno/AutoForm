package com.wyverno.commands;

import com.wyverno.commands.annotations.FillableParameter;
import com.wyverno.commands.annotations.NameParameter;

public abstract class Command {

    protected String nickname;
    @FillableParameter
    public String reason;
    @FillableParameter
    public String prefix;

    public Command(String nickname, String reason, String prefix) {
        this.nickname = nickname;
        this.reason = reason;
        this.prefix = prefix;
    }

    public abstract String getCommand();

    protected String getReasonAndPrefix() {
        return reason + " // " + prefix;
    }
}
