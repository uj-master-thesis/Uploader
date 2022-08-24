package com.edu.uj.uploader.uploader.domain.processing;

import com.edu.uj.uploader.uploader.domain.commands.Command;

public interface Processor {

    <RESULT> RESULT process(Command<RESULT, ?> command);
}
