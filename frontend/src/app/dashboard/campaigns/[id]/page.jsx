import CrudButton from "@/components/ui/buttons/CrudButton";
import fetchCampaign from "@/api/campaign/fetchCampaign";
import { CampaignStatus } from "@/enums/campaignStatus";

export default async function CampaignDetail({ params }) {

    const { id } = await params;
    const campaign = await fetchCampaign(id);


    if (!campaign) {
        return <div>Campanha não encontrada.</div>;
    }

    return (
        <div className="flex flex-col max-w-[70%] mx-auto p-6 bg-gray-200 shadow rounded-lg">
            <CrudButton name="Editar" type="edit" className="self-end" campaignid={campaign.id} />
            <h2 className="text-2xl text-black font-bold mb-4">{campaign?.title}</h2>
            <p className="mb-4 text-black"><span><b>Pesquisa:</b></span> {campaign.survey.title}</p>
            <p className="mb-4 text-black"><span><b>Status:</b></span> {campaign?.statusDescription}</p>
            <div>
                <p className="mb-4 text-black"><span><b>Contatos:</b></span></p>
                <ul className="ml-4">
                    {campaign.contacts.map(contact => (
                        <li key={contact.id} className="text-[12px]">
                            <span className="mr-2"><b>Nome:</b> {contact.fullName}</span> <span><b>Telefone:</b> {contact.phoneNumber}</span>
                        </li>
                    ))}
                </ul>
            </div>
            {campaign.statusCode === CampaignStatus.CREATED.code ? (
                <button type="button" className="bg-violet-900 p-2 w-[50%] self-center rounded text-white mt-5 hover:cursor-pointer"><b>Enviar</b></button>
            ) : null}
            <div className="text-sm mt-5 text-muted-foreground self-end">
                Criada em: {new Date(campaign?.createdAt).toLocaleDateString()}
            </div>
        </div>
    );
}
