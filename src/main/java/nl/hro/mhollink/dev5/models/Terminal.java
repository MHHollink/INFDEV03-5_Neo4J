package nl.hro.mhollink.dev5.models;

import org.neo4j.ogm.annotation.GraphId;
import org.neo4j.ogm.annotation.NodeEntity;

@NodeEntity
public class Terminal {

    @GraphId
    Long id;

    String code;
    String status;





}
