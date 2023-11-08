package com.fzhunio.school.tools;

import java.util.List;

public interface IRepository<T> {

    public List<T> list();

    public T find();
}
