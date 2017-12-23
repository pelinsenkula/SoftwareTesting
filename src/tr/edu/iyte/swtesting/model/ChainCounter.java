package tr.edu.iyte.swtesting.model;


public class ChainCounter {
	private Integer i;
	private ChainCounter chain;
	private Integer limit;
	private Boolean chainFinished = false;

	public ChainCounter(Integer start, Integer limit, ChainCounter chain) {
		this.i = start;
		this.limit = limit;
		this.chain = chain;
	}

	public void increment() {
		i++;
		if (chain != null && i > limit) {
			i = 0;
			chain.increment();
		} else if (chain == null && i > limit) {
			chainFinished = true;
		}
	}

	public Boolean isChainFinished() {
		return chainFinished;
	}

	public Integer value() {
		return i;
	}

}
