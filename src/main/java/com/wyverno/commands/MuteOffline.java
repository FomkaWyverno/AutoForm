package com.wyverno.commands;

import com.wyverno.commands.annotations.FillableParameter;

public class MuteOffline extends Command {

    private static final String COMMAND = "/muteoff";

    @FillableParameter
    public int time;

    public MuteOffline() {}

    public MuteOffline(String nickname, String reason, String prefix, int time) {
        super(nickname, reason, prefix);
        this.time = time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + this.nickname + " " + time + " " + getReasonAndPrefix();
    }
}
