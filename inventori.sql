﻿# Host: localhost  (Version: 5.5.8)
# Date: 2020-01-27 16:21:56
# Generator: MySQL-Front 5.3  (Build 4.81)

/*!40101 SET NAMES utf8 */;

#
# Structure for table "reqasset"
#

DROP TABLE IF EXISTS `reqasset`;
CREATE TABLE `reqasset` (
  `idreqasset` varchar(11) NOT NULL DEFAULT '',
  `tanggal` date NOT NULL,
  `jumlah` int(11) NOT NULL,
  `idsupplier` varchar(11) DEFAULT NULL,
  `idpetugas` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`idreqasset`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "reqasset"
#

INSERT INTO `reqasset` VALUES ('ASET0001','2019-01-15',3,'S002','7');

#
# Structure for table "reqassetdetail"
#

DROP TABLE IF EXISTS `reqassetdetail`;
CREATE TABLE `reqassetdetail` (
  `iddetailreqasset` varchar(11) NOT NULL DEFAULT '',
  `idreqasset` varchar(11) DEFAULT NULL,
  `idbarang` varchar(11) DEFAULT NULL,
  `subjumlah` int(11) DEFAULT NULL,
  `catatan` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`iddetailreqasset`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "reqassetdetail"
#

INSERT INTO `reqassetdetail` VALUES ('ASDT0001','ASET0001','A-005',5,'','Approved'),('ASDT0002','ASET0001','A-015',2,NULL,'On Waiting');

#
# Structure for table "reqpurchasing"
#

DROP TABLE IF EXISTS `reqpurchasing`;
CREATE TABLE `reqpurchasing` (
  `idreqpurchasing` varchar(11) NOT NULL DEFAULT '',
  `tanggal` date NOT NULL,
  `jumlah` int(11) NOT NULL,
  `idsupplier` varchar(11) DEFAULT NULL,
  `idpetugas` varchar(11) DEFAULT NULL,
  PRIMARY KEY (`idreqpurchasing`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Data for table "reqpurchasing"
#

INSERT INTO `reqpurchasing` VALUES ('PRCH0001','2020-01-27',4,'S002','7');

#
# Structure for table "reqpurchasingdetail"
#

DROP TABLE IF EXISTS `reqpurchasingdetail`;
CREATE TABLE `reqpurchasingdetail` (
  `iddetail` varchar(11) NOT NULL DEFAULT '',
  `idreqpurchasing` varchar(11) DEFAULT NULL,
  `idbarang` varchar(11) DEFAULT NULL,
  `subjumlah` int(11) DEFAULT NULL,
  `catatan` varchar(255) DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`iddetail`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=COMPACT;

#
# Data for table "reqpurchasingdetail"
#

INSERT INTO `reqpurchasingdetail` VALUES ('PRDT0001','PRCH0001','A-011',2,NULL,'On Waiting');

#
# Structure for table "tmbarang"
#

DROP TABLE IF EXISTS `tmbarang`;
CREATE TABLE `tmbarang` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `kode` varchar(10) NOT NULL,
  `nama` varchar(50) NOT NULL,
  `kategori` varchar(50) DEFAULT NULL,
  `satuan` varchar(20) NOT NULL,
  `stok` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

#
# Data for table "tmbarang"
#

INSERT INTO `tmbarang` VALUES (5,'A-005','Xiaomi TV',NULL,'Buah',67),(7,'S-022','LG',NULL,'Buah',10),(9,'S-021','Samsung 21 Inch',NULL,'Buah',80),(10,'A-011','Toshiba 20 Inch',NULL,'Buah',12),(11,'90AB','LCD TV Samsung',NULL,'Buah',90),(12,'F-010','Samsung 24 Inch',NULL,'Buah',80),(13,'A-001','Simbada',NULL,'Buah',70),(14,'M-001','MacBook Pro 15 Inch',NULL,'Buah',14),(15,'M-002','MacBook Pro 17 Inch',NULL,'Buah',19),(16,'C-001','Acer Aspire One 15 Inch',NULL,'Buah',15),(17,'C-002','Acer Aspire One 18 Inch',NULL,'Buah',6),(18,'A-002','Simbada Type C3',NULL,'Buah',68);

#
# Structure for table "tmkategori"
#

DROP TABLE IF EXISTS `tmkategori`;
CREATE TABLE `tmkategori` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `norak` int(5) NOT NULL DEFAULT '0',
  `nama` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

#
# Data for table "tmkategori"
#

INSERT INTO `tmkategori` VALUES (1,1,'TV'),(2,2,'DVD'),(3,10,'Sound System'),(4,5,'Joy Stick'),(5,3,'MacBook'),(6,3,'MacBook Pro'),(7,3,'MacBook Air'),(8,4,'Laptop');

#
# Structure for table "tmpetugas"
#

DROP TABLE IF EXISTS `tmpetugas`;
CREATE TABLE `tmpetugas` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(50) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `level` varchar(50) DEFAULT NULL,
  `status` enum('aktif','nonaktif') NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

#
# Data for table "tmpetugas"
#

INSERT INTO `tmpetugas` VALUES (2,'Muhamad Yusuf','admin','admin','IT Staff','aktif'),(3,'Zae','operator','8fd0959e8c40dc236d0cb1e29a05a1a91f751e5c',NULL,'aktif'),(6,'Ari','ariboss89','123456',NULL,'aktif');

#
# Structure for table "tmsupplier"
#

DROP TABLE IF EXISTS `tmsupplier`;
CREATE TABLE `tmsupplier` (
  `kode` varchar(10) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `alamat` varchar(200) NOT NULL,
  `kontak` varchar(15) NOT NULL,
  PRIMARY KEY (`kode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

#
# Data for table "tmsupplier"
#

INSERT INTO `tmsupplier` VALUES ('S002','Lenovo Inc','Jakarta','08282828'),('S004','Acer Inc','Bandung','0222'),('S005','Microsoft Inc','Jl. Pasir Kaliki','08282828'),('S008','Apple Inc','Jl. Banjar, Kota banjarmasin','0228192832');
