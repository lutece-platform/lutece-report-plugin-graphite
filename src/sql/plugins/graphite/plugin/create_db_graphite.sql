
--
-- Structure for table graphite_category
--

DROP TABLE IF EXISTS graphite_category;
CREATE TABLE graphite_category (		
id_category int(11) NOT NULL default '0',
category_title varchar(50) NOT NULL default '',
category_role varchar(255) NOT NULL default '',
category_workgroup varchar(255) NOT NULL default '',
display_front int(11) NOT NULL default '0',
display_back int(11) NOT NULL default '0',
PRIMARY KEY (id_category)
);

--
-- Structure for table graphite_graph
--

DROP TABLE IF EXISTS graphite_graph;
CREATE TABLE graphite_graph (		
id_graph int(11) NOT NULL default '0',
graph_title varchar(50) NOT NULL default '',
graph_url varchar(1000) NOT NULL default '',
graph_order int(11) NOT NULL default '0',
graph_category varchar(255) NOT NULL default '',
graph_comment varchar(255) NOT NULL default '',
PRIMARY KEY (id_graph)
);
