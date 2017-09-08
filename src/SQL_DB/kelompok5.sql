-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Sep 08, 2017 at 03:20 AM
-- Server version: 10.1.21-MariaDB
-- PHP Version: 5.6.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kelompok5`
--

-- --------------------------------------------------------

--
-- Table structure for table `bank_pbo`
--

CREATE TABLE `bank_pbo` (
  `no_rekening` varchar(11) NOT NULL,
  `pin` varchar(5) NOT NULL,
  `nama` varchar(25) NOT NULL,
  `email` varchar(30) NOT NULL,
  `alamat` text NOT NULL,
  `uang` int(13) DEFAULT '10000',
  `q_keamanan` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bank_pbo`
--

INSERT INTO `bank_pbo` (`no_rekening`, `pin`, `nama`, `email`, `alamat`, `uang`, `q_keamanan`) VALUES
('20170', '1234', 'Carolus Pandjaitan', 'carol.panj@gmail.com', 'Jl. Prof. Dr. Satrio No. 2, RT.4/RW.4, Karet Semanggi, Kota Jakarta Selatan, Daerah Khusus Ibukota Jakarta 12930\r\n', 70000, 'jakarta'),
('20171', '2233', 'danny wilimsius', 'wilimsius@d.com', 'Halim Perdana Kusumah\nKec Makasar\nJakarta Timur	', 279000, 'ayahku'),
('20172', '10001', 'denny Gunawan', 'dennygunawan', 'jl. dimana aja boleh', 304000, 'saya ga punya bapak'),
('20173', '8888', 'jeremi teti', 'jere@tet.com', 'jalan kenangan terindah\nkampung belakang\njalan raya', 130000, 'teteh'),
('20174', '123', 'gladys ivana', 'gladys@gmail.com', 'jl dreamland	', 30000, 'siapayah'),
('20176', '12345', 'edwin', 'edwinchandra54@gmail.com', 'jln.jelambar', 37000, 'ip'),
('20177', '19945', 'citramp', 'citra@ymail.com', 'dadap', 0, 'sukijan'),
('20178', '12345', 'leonardo joshua', 'rensakaki@gmail.com', 'jauh', 28500, 'markonah'),
('20179', '22129', 'Huan Pasa Adji', 'huan@gmail.com', 'jl. raya	', 43500, 'aji'),
('20180', '2323', 'rafi bli edwind', 'rbe@rbe.com', 'asdasa\ndsada\nasdas', 11000, 'bli'),
('20181', '12312', 'gladys', 'gladys@gmail.com', 'mau banget', 1000000, 'kepo'),
('20182', '2206', 'danny wilimsius', 'wilimsiusdanny@d.com', 'Halim Perdana Kusumah\nKec Makasar\nJakarta Timur	', 360000, 'ayahku'),
('20183', '12345', 'charlie liandy', 'charlieliandy@gmail.com', 'asdasd', 450000, 'agus'),
('20184', '2233', 'muhammad raffi', 'muhammadraffi@gmail.com', 'jauh', 0, 'muhammad'),
('20185', '12345', 'via', 'via@gmail.com', 'hhhhhhhh', 428000, 'miauw'),
('20186', '2222', 'tessa fanya', 'tessa@yahoo.com', 'Jalan kenangan paling indah\nJakarta Timur 1\nIndonesia', 250000, 'tissu'),
('20187', '9090', 'lkjasdlkjasd', 'poiuytrewq@asd.com', 'kkkkkkkkkkk\nasda\na\nasdasdasdasdasd\nasdasdasdasd', 400000, 'jakarta');

-- --------------------------------------------------------

--
-- Table structure for table `pembayaran`
--

CREATE TABLE `pembayaran` (
  `no_rekening` varchar(10) NOT NULL,
  `nom` varchar(12) NOT NULL,
  `hargaByr` int(11) NOT NULL,
  `perihal` varchar(20) NOT NULL,
  `tanggalTransaksi` varchar(11) NOT NULL,
  `jamTransaksi` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pembayaran`
--

INSERT INTO `pembayaran` (`no_rekening`, `nom`, `hargaByr`, `perihal`, `tanggalTransaksi`, `jamTransaksi`) VALUES
('20171', '08977732231', 100000, 'Pulsa HP', '25/5/2017', '23:49'),
('20186', '089544556688', 100000, 'Pulsa HP', '26/5/2017', '15:59'),
('20187', '098877666666', 50000, 'Pulsa HP', '4/9/2017', '15:47');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi`
--

CREATE TABLE `transaksi` (
  `no_rekeningPengirim` varchar(11) NOT NULL,
  `jumlahUangTransfer` int(13) NOT NULL,
  `no_rekeningPenerima` varchar(11) NOT NULL,
  `namaPenerima` varchar(35) NOT NULL,
  `tanggalTransaksi` varchar(11) NOT NULL,
  `jamTransaksi` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `transaksi`
--

INSERT INTO `transaksi` (`no_rekeningPengirim`, `jumlahUangTransfer`, `no_rekeningPenerima`, `namaPenerima`, `tanggalTransaksi`, `jamTransaksi`) VALUES
('20171', 30000, '20174', 'gladys ivana', '20/5/2017', '3:35'),
('20171', 43500, '20179', 'Huan Pasa Adji', '20/5/2017', '3:39'),
('20185', 229000, '20171', 'danny wilimsius', '20/5/2017', '3:58'),
('20185', 22000, '20178', 'leonardo joshua', '20/5/2017', '4:1'),
('20186', 150000, '20171', 'danny wilimsius', '26/5/2017', '15:59'),
('20187', 50000, '20170', 'Carolus Pandjaitan', '4/9/2017', '15:47');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bank_pbo`
--
ALTER TABLE `bank_pbo`
  ADD PRIMARY KEY (`no_rekening`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
