package com.wyverno.commands;

import com.wyverno.commands.annotations.FillableParameter;

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

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public abstract String getCommand();

    protected String getReasonAndPrefix() {
        return reason + " // " + prefix;
    }
}
