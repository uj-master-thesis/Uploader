package com.edu.uj.uploader.uploader.domain.commands;

import static com.edu.uj.uploader.uploader.domain.commands.Reason.EMPTY;
import static com.edu.uj.uploader.uploader.domain.commands.Result.FAIL;
import static com.edu.uj.uploader.uploader.domain.commands.Result.SUCCESS;

public class ResultWithReason {
    private final Result result;
    private final Reason reason;

    public ResultWithReason(Result result, Reason reason) {
        this.result = result;
        this.reason = reason;
    }

    public static ResultWithReason of(Result result, Reason reason) {
        return new ResultWithReason(result, reason);
    }

    public static ResultWithReason ofSuccess() {
        return new ResultWithReason(SUCCESS, EMPTY);
    }

    public static ResultWithReason ofFailure() {
        return new ResultWithReason(FAIL, EMPTY);
    }

    public Result getResult() {
        return result;
    }

    public Reason getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) return true;
        if (!(o instanceof ResultWithReason)) return false;

        ResultWithReason that = (ResultWithReason) o;

        if (result != that.result) return false;
        return reason == that.reason;
    }

    @Override
    public int hashCode() {
        int result1 = result != null ? result.hashCode() : 0;
        result1 = 31 * result1 + (reason != null ? reason.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "ResultWithReason{" +
                "result=" + result +
                ", reason=" + reason +
                '}';
    }
}
