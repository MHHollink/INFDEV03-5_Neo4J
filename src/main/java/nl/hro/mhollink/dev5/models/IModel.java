package nl.hro.mhollink.dev5.models;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public interface IModel {

    Long getId();
    void setId(Long id);

    String toString();
}
