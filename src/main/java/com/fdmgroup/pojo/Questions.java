package com.fdmgroup.pojo;

import com.fdmgroup.enumeratedtypes.SecurityQuestion;

public class Questions {

	SecurityQuestion question;
	String answer;
	
	public Questions(){}
	public Questions(SecurityQuestion question,String answer){
		this.question=question;
		this.answer=answer;
	}

	public SecurityQuestion getQuestion() {
		return question;
	}

	public void setQuestion(SecurityQuestion question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((answer == null) ? 0 : answer.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Questions other = (Questions) obj;
		if (answer == null) {
			if (other.answer != null)
				return false;
		} else if (!answer.equals(other.answer))
			return false;
		if (question != other.question)
			return false;
		return true;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Questions [question=").append(question).append(", answer=").append(answer).append("]");
		return builder.toString();
	}
	
	
}
