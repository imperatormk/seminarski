package com.fikt.seminarski.persistence;

import java.util.Optional;
import java.util.UUID;

import com.fikt.seminarski.model.Token;
import com.fikt.seminarski.model.User;
import com.fikt.seminarski.model.OptionalUser;

public interface TokenDAO {
	public Token findOrCreateTokenForUser(int userId);
	public Optional<Token> findTokenForUser(User user);
	public Optional<Token> findTokenForUser(OptionalUser user, int par1);
	public Optional<Token> expireToken(Token token);
}
