package com.wyverno.commands;

import com.wyverno.commands.annotations.FillableParameter;

public class JailOffline extends Command {

    private static final String COMMAND = "/jailoff";

    @FillableParameter
    public int time;

    public JailOffline(String nickname, String reason, String prefix, int time) {
        super(nickname, reason, prefix);
        this.time = time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    @Override
    public String getCommand() {
        return COMMAND + " " + time + " " + this.getReasonAndPrefix();
    }
}
