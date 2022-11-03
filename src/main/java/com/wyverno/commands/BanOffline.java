package com.wyverno.commands;

import com.wyverno.commands.annotations.FillableParameter;

public class BanOffline extends Command {

    private static final String COMMAND = "/banoff";

    @FillableParameter
    public int typeBan;
    @FillableParameter
    public int days;

    public BanOffline() {}

    public BanOffline(String nickname, String reason, String prefix, int typeBan, int days) {
        super(nickname, reason, prefix);
        this.typeBan = typeBan;
        this.days = days;
    }

    public void setTypeBan(int typeBan) {
        this.typeBan = typeBan;
    }

    public void setDays(int days) {
        this.days = days;
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + this.typeBan + " " + this.nickname + " " + this.days + " " + this.getReasonAndPrefix();
    }
}
