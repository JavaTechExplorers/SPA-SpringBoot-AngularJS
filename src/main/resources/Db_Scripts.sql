CREATE TABLE `employee` (
  `EMP_ID` int(11) NOT NULL AUTO_INCREMENT,
  `REMARKS` varchar(255) DEFAULT NULL,
  `GENDER` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`EMP_ID`),
  UNIQUE KEY `EMP_ID_UNIQUE` (`EMP_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=1015 DEFAULT CHARSET=latin1;

CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL AUTO_INCREMENT,
  `MENU_CODE` varchar(6) NOT NULL,
  `MENU_NAME` varchar(45) NOT NULL,
  `DISPLAY_NAME` varchar(45) NOT NULL,
  `URL` varchar(45) NOT NULL,
  `CREATED_BY` varchar(10) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`MENU_ID`),
  UNIQUE KEY `MENE_ID_UNIQUE` (`MENU_ID`),
  UNIQUE KEY `MENU_CODE_UNIQUE` (`MENU_CODE`),
  UNIQUE KEY `URL_UNIQUE` (`URL`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

CREATE TABLE `sys_role` (
  `ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ROLE_NAME` varchar(255) NOT NULL,
  `ROLE_DESC` varchar(255) DEFAULT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `ROLE_ID_UNIQUE` (`ROLE_ID`),
  UNIQUE KEY `ROLE_NAME_UNIQUE` (`ROLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `sys_role_menu_map` (
  `ROLE_MENU_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  `MENU_ID` int(11) NOT NULL,
  `CREATE_BY` varchar(45) NOT NULL,
  `CREATE_DATE` datetime NOT NULL,
  PRIMARY KEY (`ROLE_MENU_ID`),
  KEY `ROLE_FOREIGN_KEY_idx` (`ROLE_ID`),
  CONSTRAINT `FOREIGN_KEY_ROLE_ID` FOREIGN KEY (`ROLE_ID`) REFERENCES `sys_role` (`ROLE_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `sys_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_NAME` varchar(10) NOT NULL,
  `USER_PASSWORD` varchar(255) NOT NULL,
  `FIRST_NAME` varchar(255) NOT NULL,
  `LAST_NAME` varchar(255) NOT NULL,
  `MAIL_ID` varchar(255) DEFAULT NULL,
  `PHONE_NUM` varchar(45) DEFAULT NULL,
  `CREATED_BY` varchar(45) NOT NULL,
  `CREATED_DATE` datetime NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_NAME_UNIQUE` (`USER_NAME`),
  UNIQUE KEY `MAIL_ID_UNIQUE` (`MAIL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `sys_user_role_map` (
  `USER_ROLE_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  `CREATE_BY` varchar(45) DEFAULT NULL,
  `CREATE_DATE` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ROLE_ID`),
  KEY `FOREIGN_JEY_USER_ID_idx` (`USER_ID`),
  KEY `FOREIGN_KEY_ROLE_ID_idx` (`ROLE_ID`),
  CONSTRAINT `FOREIGN_JEY_USER_ID` FOREIGN KEY (`USER_ID`) REFERENCES `sys_user` (`USER_ID`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;