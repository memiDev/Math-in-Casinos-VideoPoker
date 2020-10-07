package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import blackjack.Game;

public class Main {

	public static boolean isInteger(String input) {
		try {
			Integer.parseInt(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean isFloat(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static void main(String[] args) throws Exception {

		String c = null;
		String[] debug = null;
		String advice_bs = null;
		String advice_hl = null;
		int aux1 = 0;
		int shoe = 0;
		int shuffle = 0;
		int s_number = 0;
		int number_shuffle = 0;
		int endDebugMode = -1;
		boolean hilo_count = false;
		boolean advice = false;
		boolean firstgame = true;
		boolean exit = false;
		boolean bust = false;
		boolean doneBet = false;
		boolean doneDeal = false;
		boolean doneGame = false;
		boolean splitting = false;
		boolean doubledown = false;
		boolean surrender = true;
		boolean debugmode = false;
		boolean interactivemode = false;
		boolean simulationmode = false;
		boolean error = false;
		boolean errorinsurance = false;
		boolean BS = false;
		boolean BS_AF = false;
		boolean HL = false;
		boolean HL_AF = false;
		float bet = 0;
		float maxbet = 0;
		float minbet = 0;
		float acefive_bet = 0;
		int checkDealerGame = -1;
		int check_hilo=0;
		float balance = 0;
		BufferedReader br = null;
		BufferedReader reader = null;
		Game game = new Game();
		
		String path = System.getProperty("user.dir");

		if (args.length < 6 || args.length > 8 || args.length == 7) {
			System.out.println("not enough arguments");
			System.exit(0);
		} else {
			if (args.length == 6) {
				if (args[0].equals("-i")) {
					interactivemode = true;
				} else if (args[0].equals("-d")) {
					debugmode = true;
				} else {
					System.out.println("illegal arguments");
					System.exit(0);
				}
				for (int i = 1; i < 6; i++) {
					if (isInteger(args[i]))
						aux1++;
				}
			}
			if (aux1 == 5 && interactivemode) {
				if (Integer.parseInt(args[1]) >= 1 && Integer.parseInt(args[2]) >= 10 * Integer.parseInt(args[1])
						&& Integer.parseInt(args[3]) >= 50 * Integer.parseInt(args[1])
						&& Integer.parseInt(args[2]) <= 20 * Integer.parseInt(args[1]) && Integer.parseInt(args[4]) >= 4
						&& Integer.parseInt(args[4]) <= 8 && Integer.parseInt(args[5]) >= 10
						&& Integer.parseInt(args[5]) <= 100) {
					minbet = Integer.parseInt(args[1]);
					maxbet = Integer.parseInt(args[2]);
					balance = Integer.parseInt(args[3]);
					shoe = Integer.parseInt(args[4]);
					shuffle = Integer.parseInt(args[5]);
					game = new Game(minbet, shoe, balance);
				} else {
					System.out.println("illegal arguments");
					System.exit(0);
				}
			} else if (aux1 == 3 && debugmode) {
				if (args[4].equals("shoe-file.txt") && args[5].equals("cmd-file.txt")) {
					if (Integer.parseInt(args[1]) >= 1 && Integer.parseInt(args[2]) >= 10 * Integer.parseInt(args[1])
							&& Integer.parseInt(args[3]) >= 50 * Integer.parseInt(args[1])
							&& Integer.parseInt(args[2]) <= 20 * Integer.parseInt(args[1])) {
						minbet = Integer.parseInt(args[1]);
						maxbet = Integer.parseInt(args[2]);
						balance = Integer.parseInt(args[3]);
						game.createShoe(minbet, args[4], balance);
					} else {
						System.out.println("illegal arguments");
						System.exit(0);
					}

				} else {
					System.out.println("illegal arguments");
					System.exit(0);
				}

			} else {
				if (args[0].equals("-s")) {
					simulationmode = true;
				} else {
					System.out.println("illegal arguments");
					System.exit(0);
				}
				if (simulationmode) {
					if (Integer.parseInt(args[1]) >= 1 && Integer.parseInt(args[2]) >= 10 * Integer.parseInt(args[1])
							&& Integer.parseInt(args[3]) >= 50 * Integer.parseInt(args[1])
							&& Integer.parseInt(args[2]) <= 20 * Integer.parseInt(args[1])
							&& Integer.parseInt(args[4]) >= 4 && Integer.parseInt(args[4]) <= 8
							&& Integer.parseInt(args[5]) >= 10 && Integer.parseInt(args[5]) <= 100
							&& Integer.parseInt(args[6]) > 0 && (args[7].equals("BS") || args[7].equals("BS-AF")
									|| args[7].equals("HL") || args[7].equals("HL-AF"))) {
						minbet = Integer.parseInt(args[1]);
						maxbet = Integer.parseInt(args[2]);
						balance = Integer.parseInt(args[3]);
						shoe = Integer.parseInt(args[4]);
						shuffle = Integer.parseInt(args[5]);
						s_number = Integer.parseInt(args[6]);
						if (args[7].equals("BS")) {
							BS = true;
						} else if (args[7].equals("BS-AF")) {
							BS_AF = true;
						} else if (args[7].equals("HL")) {
							HL = true;
						} else if (args[7].equals("HL-AF")) {
							HL_AF = true;
						} else {
							System.out.println("illegal arguments");
							System.exit(0);
						}
					}
				}
			}
		}

		if (interactivemode) {
			System.out.println("Shuffling the shoe...");
			br = new BufferedReader(new InputStreamReader(System.in));
		} else if (debugmode) {
			reader = new BufferedReader(new FileReader(path + "/" + args[5]));
			c = reader.readLine();
			debug = c.split(" ");
		} else if (simulationmode) {
			System.out.println("Shuffling the shoe...");
		}

		while (!exit) {
			try {
				String[] aux;
				if (balance <= 0 && !doneBet && !doneDeal) {
					exit = true;
				}
				if (interactivemode) {
					c = br.readLine();
				} else if (debugmode) {
					if (endDebugMode >= debug.length - 1) {
						exit = true;
						break;
					}
					endDebugMode++;
					c = debug[endDebugMode].toString();
					if (c.equals("b")) {
						if (isInteger(debug[endDebugMode + 1].toString())) {
							c = c.concat(" " + debug[endDebugMode + 1].toString());
							endDebugMode++;
							game.newBet(Integer.parseInt((debug[endDebugMode].toString())));
						}
					}
					System.out.println("\n-cmd " + c);
				} else if (simulationmode) {
					if (s_number == number_shuffle || balance == 0) {
						c = "st";
					} else {
						if (doneBet != true) {
							if (c == null) {
								c = "b " + minbet;

							} else if (BS_AF || HL_AF) {
								if (c.equals("ad"))
									c = "b " + acefive_bet;
								else
									c = c.replace(c, "ad");

							} else {

								if (game.simulatewhowins() < 0) {
									if (bet == minbet) {

									} else {
										if ((bet -= Math.abs(game.simulatewhowins()) * minbet) < minbet) {
											bet = minbet;

										}

									}
								} else if (game.simulatewhowins() > 0) {
									if (bet == maxbet) {

									} else {
										if ((bet += game.simulatewhowins() * minbet) > maxbet) {
											bet = maxbet;

										}
									}
								}
								if(bet < minbet)
									bet = minbet;
								c = "b " + bet;
							}
						} else if (doneDeal == false) {
							c = c.replace(c, "d");

						} else if (doneBet && doneDeal && !advice) {
							c = c.replace(c, "ad");
							advice = false;

						} else {
							if (BS || BS_AF) {
								String bs = null;
								if (error) {
									if (game.checkSum() < 17) {
										bs = "h";
										c = c.replace(c, bs);
									} else {
										bs = "s";
										c = c.replace(c, bs);
									}
								} else if ((c.equals("ad"))) {
									if (advice_bs.equals("stand"))
										bs = "s";
									else if (advice_bs.equals("hit"))
										bs = "h";
									else if (advice_bs.equals("surrender"))
										bs = "u";
									else if (advice_bs.equals("double"))
										bs = "2";
									else if (advice_bs.equals("split"))
										bs = "p";
									c = c.replace(c, bs);
								} else {
									c = c.replace(c, "ad");
								}
							} else if (HL || HL_AF) {
								String hl = null;
								if (error) {
									if (game.checkSum() < 17) {
										hl = "h";
										c = c.replace(c, hl);
									} else {
										hl = "s";
										c = c.replace(c, hl);
									}
								} else if ((c.equals("ad")) || c.equals("i") || errorinsurance) {
									errorinsurance = false;
									if (advice_hl.contains("error")) {
										if (advice_hl.contains("Insurance")) {
											if (c.equals("i")) {
												if (advice_bs.contains("stand"))
													hl = "s";
												else if (advice_bs.contains("hit"))
													hl = "h";
												else if (advice_bs.contains("surrender"))
													hl = "u";
												else if (advice_bs.contains("double"))
													hl = "2";
												else if (advice_bs.contains("split"))
													hl = "p";
											}
											hl = "i";
										} else {
											if (advice_bs.equals("stand"))
												hl = "s";
											else if (advice_bs.equals("hit"))
												hl = "h";
											else if (advice_bs.equals("surrender"))
												hl = "u";
											else if (advice_bs.equals("double"))
												hl = "2";
											else if (advice_bs.equals("split"))
												hl = "p";
										}
									} else {
										if (advice_hl.contains("basic")) {
											if (advice_hl.contains("Insurance")) {
												if (c.equals("i")) {
													if (advice_bs.equals("stand"))
														hl = "s";
													else if (advice_bs.equals("hit"))
														hl = "h";
													else if (advice_bs.equals("surrender"))
														hl = "u";
													else if (advice_bs.equals("double"))
														hl = "2";
													else if (advice_bs.equals("split"))
														hl = "p";
												} else {
													hl = "i";
												}
											} else {
												if (advice_bs.equals("stand"))
													hl = "s";
												else if (advice_bs.equals("hit"))
													hl = "h";
												else if (advice_bs.equals("surrender"))
													hl = "u";
												else if (advice_bs.equals("double"))
													hl = "2";
												else if (advice_bs.equals("split"))
													hl = "p";
											}
										} else {
											if (advice_hl.contains("Insurance")) {
												if (advice_hl.contains(" ")) {
													if (advice_hl.contains("stand"))
														hl = "s";
													else if (advice_hl.contains("hit"))
														hl = "h";
													else if (advice_hl.contains("surrender"))
														hl = "u";
													else if (advice_hl.contains("double"))
														hl = "2";
													else if (advice_hl.contains("split"))
														hl = "p";
												} else {
													if (c.equals("i")) {
														if (advice_bs.equals("stand"))
															hl = "s";
														else if (advice_bs.equals("hit"))
															hl = "h";
														else if (advice_bs.equals("surrender"))
															hl = "u";
														else if (advice_bs.equals("double"))
															hl = "2";
														else if (advice_bs.equals("split"))
															hl = "p";
													} else {
														hl = "i";
													}

												}
											} else {
												if (advice_hl.contains("stand"))
													hl = "s";
												else if (advice_hl.contains("hit"))
													hl = "h";
												else if (advice_hl.contains("surrender"))
													hl = "u";
												else if (advice_hl.contains("double"))
													hl = "2";
												else if (advice_hl.contains("split"))
													hl = "p";
											}
										}

									}

									c = hl;
								} else {
									c = c.replace(c, "ad");
								}

							}
						}
					}
				}

				if (c.startsWith("b")) {
					if (doneBet == true) {
						System.out.println("b: illegal command");
						continue;
					}
					if (c.length() > 1) {
						if (c.substring(1, 2).matches(" ")) {
							aux = c.split(" ");
							if (aux.length < 2) {
								if (firstgame) {

									if (bet > maxbet || bet < minbet) {
										if(simulationmode || debugmode){
											if(bet > maxbet)
												bet=maxbet;
											if(bet<minbet)
												bet=minbet;
										}else{
											System.out.println("b: illegal command");
											continue;
										}
									}
									if (bet > balance) {
										if (balance < minbet)
											exit = true;
										else if (simulationmode || debugmode) {
											bet = balance;
										} else {
											System.out.println("b: illegal command");
											continue;
										}
									}
									if (interactivemode)
										game = new Game(bet, shoe, balance);
									firstgame = false;
									doneBet = true;
								} else {
									if (bet > maxbet || bet < minbet) {
										if(simulationmode || debugmode){
											if(bet > maxbet)
												bet=maxbet;
											if(bet<minbet)
												bet=minbet;
										}else{
											System.out.println("b: illegal command");
											continue;
										}
									}
									if (bet > balance) {
										if (balance < minbet)
											exit = true;
										else if (simulationmode || debugmode) {
											bet = balance;
										} else {
											System.out.println("b: illegal command");
											continue;
										}
									}
									game.newBet(bet);
									doneBet = true;
								}
								System.out.println("Player is betting " + bet);
								continue;
							}
							if (isInteger(aux[1]) || isFloat(aux[1])) {
								if (firstgame) {
									if (isInteger(aux[1])) {
										bet = Integer.parseInt(aux[1]);
									}
									if (isFloat(aux[1])) {
										bet = (float) Double.parseDouble(aux[1]);
									}

									if (bet > maxbet || bet < minbet) {
										if(simulationmode || debugmode){
											if(bet > maxbet)
												bet=maxbet;
											if(bet<minbet)
												bet=minbet;
										}else{
											System.out.println("b: illegal command");
											continue;
										}
									}
									if (bet > balance) {
										if (balance < minbet)
											exit = true;
										else if (simulationmode || debugmode) {
											bet = balance;
										} else {
											System.out.println("b: illegal command");
											continue;
										}
									}
									if (interactivemode || simulationmode)
										game = new Game(bet, shoe, balance);
									balance -= bet;
									firstgame = false;
									System.out.println("Player is betting " + bet);
									doneBet = true;
								} else {
									if (isInteger(aux[1])) {
										bet = Integer.parseInt(aux[1]);
									}
									if (isFloat(aux[1])) {
										bet = (float) Double.parseDouble(aux[1]);
									}
									if (bet > maxbet || bet < minbet) {
										if(simulationmode || debugmode){
											if(bet > maxbet)
												bet=maxbet;
											if(bet<minbet)
												bet=minbet;
										}else{
											System.out.println("b: illegal command");
											continue;
										}
									}
									if (bet > balance) {
										if (balance < minbet)
											exit = true;
										else if (simulationmode || debugmode) {
											bet = balance;
										} else {
											System.out.println("b: illegal command");
											continue;
										}
									}
									game.newBet(bet);
									balance -= bet;
									System.out.println("Player is betting " + bet);
									doneBet = true;
								}
							} else {
								System.out.println("b: illegal command");
								continue;
							}
						} else {
							System.out.println("b: illegal command");
							continue;
						}
					} else {
						bet = minbet;
						if (firstgame) {
							if (bet > maxbet || bet < minbet) {
								if(simulationmode || debugmode){
									if(bet > maxbet)
										bet=maxbet;
									if(bet<minbet)
										bet=minbet;
								}else{
									System.out.println("b: illegal command");
									continue;
								}
							}
							if (bet > balance) {
								if (balance < minbet)
									exit = true;
								else if (simulationmode || debugmode) {
									bet = balance;
								} else {
									System.out.println("b: illegal command");
									continue;
								}
							}
							if (interactivemode)
								game = new Game(bet, shoe, balance);
							firstgame = false;
							balance -= bet;
							doneBet = true;
						} else {
							if (bet > balance) {
								if (balance < minbet)
									exit = true;
								else if (simulationmode || debugmode) {
									bet = balance;
								} else {
									System.out.println("b: illegal command");
									continue;
								}
							}
							if (bet > maxbet || bet < minbet) {
								if(simulationmode || debugmode){
									if(bet > maxbet)
										bet=maxbet;
									if(bet<minbet)
										bet=minbet;
								}else{
									System.out.println("b: illegal command");
									continue;
								}
							}
							game.newBet(bet);
							balance -= bet;
							doneBet = true;
						}
						System.out.println("Player is betting " + bet);

					}
					continue;
				}

				switch (c) {

				case "$":
					System.out.println("player current balance is " + balance);
					break;

				case "d":
					if (doneBet != true || doneDeal == true) {
						System.out.println("d: illegal command");
						break;
					}
					game.deal(balance);
					game.printFirstHandDealer();
					game.printHandGame("player");
					game.verifywin();
					doneDeal = true;
					doneGame = false;
					break;

				case "s":
					if ((doneBet == true && doneDeal == false) || firstgame == true || doneGame == true) {
						System.out.println("s: illegal command");
						break;
					}
					game.stand();
					surrender = false;
					if (game.checkActualHand()) {
						game.checkWhoWins(); // random
						game.incrementPlayerActualHand();
						game.printHandPlaying();
						game.printHandGame("player");
					} else {
						checkDealerGame = game.dealerPlay();
						if (checkDealerGame == 0) {
							game.checkWhoWins(); // Dealer bust
							doneGame = true; // ganha jogador
							break;
						}
						game.checkWhoWins(); // random
						doneGame = true;
						break;
					}
					check_hilo=0;
					break;

				case "h":
					if ((doneBet == true && doneDeal == false) || firstgame == true || doneGame == true) {
						System.out.println("h: illegal command");
						break;
					}
					surrender = false;
					game.hit();
					game.printHandGame("player");
					bust = game.checkbust();
					if (bust) {
						game.printBust();
						if (game.checkActualHand()) {
							game.checkWhoWins();
							game.incrementPlayerActualHand();
							game.printHandPlaying();
							game.printHandGame("player");
						} else {
							game.checkWhoWins(); // jogador bust dealer ganha
							game.printHandGame("dealer");
							doneGame = true;
							break;
						}

					}
					check_hilo=0;
					break;

				case "p":
					if ((doneBet == true && doneDeal == false) || firstgame == true || doneGame == true) {
						if (simulationmode) {
							error = true;
							continue;
						}
						System.out.println("p: illegal command");
						break;
					}
					check_hilo=0;

					surrender = false;
					splitting = game.splitting();
					if (splitting) {
						balance -= bet;
					} else {
						if (simulationmode) {
							error = true;
							continue;
						}
						System.out.println("p: illegal command");
						break;
					}
					break;

				case "q":
					exit = true;
					System.out.println("bye!");
					break;

				case "i": // insurance
					if ((doneBet == true && doneDeal == false) || firstgame == true || doneGame == true) {
						if (simulationmode) {
							errorinsurance = true;
							continue;
						}
						System.out.println("i: illegal command");
						break;
					}
					check_hilo=0;
					boolean check = game.insurance();
					if (check) {
						balance -= bet;
					} else {
						if (simulationmode) {
							errorinsurance = true;
							continue;
						}
					}
					break;

				case "u": // surrender
					if ((doneBet == true && doneDeal == false) || firstgame == true || doneGame == true
							|| surrender == false) {
						if (simulationmode) {
							error = true;
							continue;
						}
						System.out.println("u: illegal command");
						break;
					}
					check_hilo=0;
					game.surrender();
					game.playerlose();
					game.printHandGame("dealer");
					doneGame = true;
					balance += 0.5 * bet;
					break;

				case "2": // double
					if ((doneBet == true && doneDeal == false) || firstgame == true || doneGame == true) {
						if (simulationmode) {
							error = true;
							continue;
						}
						System.out.println("2: illegal command");
						break;
					}
					check_hilo=0;
					surrender = false;
					doubledown = game.doublingDown();
					if (doubledown) {
						balance -= bet;
						if (game.checkActualHand()) {
							game.checkWhoWins(); // random
							game.incrementPlayerActualHand();
							game.printHandPlaying();
							game.printHandGame("player");
						} else {
							checkDealerGame = game.dealerPlay();
							if (checkDealerGame == 0) {
								game.checkWhoWins();
								doneGame = true;
								break;
							}
							game.checkWhoWins();
							doneGame = true;
							break;
						}

					} else {
						if (simulationmode) {
							error = true;
							continue;
						}
					}
					break;

				case "ad": // advice

					if (doneBet == false && doneDeal == false) {
						acefive_bet = game.acefive(bet, minbet, maxbet);
						System.out.println("acefive          " + acefive_bet);
						break;
					}

					if (doneBet != true || doneDeal != true) {
						System.out.println("ad: illegal command");
						break;
					}
					advice = true;
					advice_bs = game.basic_advice();
					System.out.println("basic			" + advice_bs);

					hilo_count = true;
					if(check_hilo != 0){
						if (advice_hl.contains("error")) {
							if (advice_hl.contains("Insurance")) {
								System.out.println("hilo        " + advice_bs + " Insurance");
							}
							System.out.println("hilo			" + advice_bs);
						} else {
							if (advice_hl.contains("basic")) {
								if (advice_hl.contains("Insurance")) {
									System.out.println("hilo       		" + advice_bs + "Insurance");
								} else {
									System.out.println("hilo       		" + advice_bs);
								}
							} else {
								if (advice_hl.contains("Insurance")) {
									if (advice_hl.contains(" ")) {
										System.out.println("hilo       		" + advice_hl);
									} else {
										System.out.println("hilo       		" + advice_bs + " Insurance");
									}
								} else {
									System.out.println("hilo       		" + advice_hl);
								}
							}

						}
						continue;
					}
					check_hilo++;
					advice_hl = game.hilo_advice();
					if (advice_hl.contains("error")) {
						if (advice_hl.contains("Insurance")) {
							System.out.println("hilo        " + advice_bs + " Insurance");
						}
						System.out.println("hilo			" + advice_bs);
					} else {
						if (advice_hl.contains("basic")) {
							if (advice_hl.contains("Insurance")) {
								System.out.println("hilo       		" + advice_bs + "Insurance");
							} else {
								System.out.println("hilo       		" + advice_bs);
							}
						} else {
							if (advice_hl.contains("Insurance")) {
								if (advice_hl.contains(" ")) {
									System.out.println("hilo       		" + advice_hl);
								} else {
									System.out.println("hilo       		" + advice_bs + " Insurance");
								}
							} else {
								System.out.println("hilo       		" + advice_hl);
							}
						}

					}

					break;

				case "st": // statistics
					if (doneBet == true && doneDeal == true) {
						System.out.println("st: illegal command");
						break;
					}
					game.printStats();
					if (simulationmode)
						exit = true;
					break;
				default:
					System.out.println("illegal command");
					break;

				}

				if (doneGame == true && doneBet == true && doneDeal == true) {
					balance = game.printFinalGame(balance);
					doneBet = false;
					doneDeal = false;
					splitting = false;
					surrender = true;
					advice = false;
					error = false;
					if (hilo_count == false) {
						game.count_cards_hilo();
					}
					hilo_count = false;
					game.hilo_countrestcard();
					game.setFinalRunningCount();
					game.count_cards_acefive();
					check_hilo=0;
					if (interactivemode || simulationmode)
						if (game.reshuffle((float) shuffle))
							number_shuffle++;
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}
}
