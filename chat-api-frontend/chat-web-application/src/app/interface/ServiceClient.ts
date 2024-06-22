export interface ServiceClient{
    id: number,
    utilisateurID: number,
    typeContact: string,
    message: string,
    dateContact: Date,
    status: string;
}