package com.netcracker.recipeproject.server.model.Command;

import com.netcracker.recipeproject.library.Message;

public interface Command {
    Message Execute(Message message);
}
