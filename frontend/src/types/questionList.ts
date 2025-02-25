/**错题集 */
export interface GetQuestion {
  id: number;
}


export interface Question{
    id: number;
    tags: string[];
    difficulty: number;
    content: string;
    answer: string;
}
